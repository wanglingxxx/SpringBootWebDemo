	var aLi1=document.getElementsByClassName('nav-li');
    var aDiv1=document.getElementsByClassName('pannal-nav');
   // var aConT=document.getElementsByClassName('content-title')[0];
    //var aP=aConT.getElementsByTagName('p');
    for(i=0;i<aLi1.length;i++){
        aLi1[i].index=i;
        aLi1[i].onclick=function(){
            for(j=0;j<aDiv1.length;j++){
               // aP[j].className=" ";
                aDiv1[j].className="pannal-nav pannal-nav-"+(j+1)+" hidden";        
            }
            aDiv1[this.index].className="pannal-nav pannal-nav-"+(this.index+1)+" show";      
         
        }
    }
    