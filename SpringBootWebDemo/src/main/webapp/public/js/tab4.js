	var aLi4=document.getElementsByClassName('ach-title-li');
    var aDiv4=document.getElementsByClassName('pannal-ach');
   // var aConT=document.getElementsByClassName('content-title')[0];
    //var aP=aConT.getElementsByTagName('p');
    for(i=0;i<aLi4.length;i++){
        aLi4[i].index=i;
        aLi4[i].onclick=function(){
            for(j=0;j<aDiv4.length;j++){
               // aP[j].className=" ";
                aDiv4[j].className="pannal-ach pannal-ach-"+(j+1)+" hidden";        
            }
            aDiv4[this.index].className="pannal-ach pannal-ach-"+(this.index+1)+" show";   
        }
    }
