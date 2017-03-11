package com.research.cache.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Adm on 2016/12/12.
 */
public class SerializeUtils {

    private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);


    public static Object deserialize(byte[] bytes){
        Object result = null;

        if(isEmpty(bytes)){
            return null;
        }

        ByteArrayInputStream byteStream = null;
        ObjectInputStream objectInputStream = null;
        try{
            byteStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteStream);
            result = objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception ex){

        } finally{
            CloseUtil.close(objectInputStream);
            CloseUtil.close(byteStream);
        }
        return result;
    }

    public static boolean isEmpty(byte[] data){
        return (data == null || data.length == 0);
    }

    /**
     * 序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object){
        byte[] result = null;

        if(object == null){
            return new byte[0];
        }

        ByteArrayOutputStream byteStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            if(!(object instanceof Serializable)){
                throw new IllegalArgumentException(
                    SerializeUtils.class.getSimpleName() + " requires a Serializable payload " +
                            "but received an object of type [" + object.getClass().getName() + "]");

            }
            byteStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            result = byteStream.toByteArray();
            objectOutputStream.close();
            byteStream.close();

        }catch (Exception ex){

        }finally {
            CloseUtil.close(objectOutputStream);
            CloseUtil.close(byteStream);
        }
        return result;
    }

    private static byte[] compress(byte[] in){
        if(in == null){
            throw new NullPointerException("Can't compress null ");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gz = null;
        try{
            gz = new GZIPOutputStream(bos);
            gz.write(in);
        } catch (IOException e){
            throw new RuntimeException("IO exception compressing data" , e);
        } finally{
            CloseUtil.close(gz);
            CloseUtil.close(bos);
        }
        byte[] rv = bos.toByteArray();
        return rv;
    }

    /**
     *
     * @param in
     * @return
     */
    private static byte[] decompress(byte[] in) {
        ByteArrayOutputStream bos = null;
        if (in != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(in);
            bos = new ByteArrayOutputStream();
            GZIPInputStream gis = null;
            try {
                gis = new GZIPInputStream(bis);

                byte[] buf = new byte[8192];

                int r = -1;
                while ((r = gis.read(buf)) > 0) {
                    bos.write(buf);
                }

            } catch (IOException e) {
                bos = null;
            } finally {
                CloseUtil.close(gis);
                CloseUtil.close(bis);
                CloseUtil.close(bos);
            }
        }
        return bos == null ? null : bos.toByteArray();
    }
    private static final class CloseUtil {
        private CloseUtil() {
        }

        public static void close(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    logger.warn("Unable to close " + e.getMessage(), e);
                }
            }
        }
    }
}
