	var aLi2=document.getElementsByClassName('policy-title-li');
    var aDiv2=document.getElementsByClassName('pannal-policy');
   // var aConT=document.getElementsByClassName('content-title')[0];
    //var aP=aConT.getElementsByTagName('p');
    for(i=0;i<aLi2.length;i++){
        aLi2[i].index=i;
        aLi2[i].onclick=function(){
            for(j=0;j<aDiv2.length;j++){
               // aP[j].className=" ";
                aDiv2[j].className="pannal-policy pannal-policy-"+(j+1)+" hidden";        
            }
            aDiv2[this.index].className="pannal-policy pannal-policy-"+(this.index+1)+" show"; 
           
           
         
        }
    }