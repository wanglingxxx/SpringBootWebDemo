	
    var aLi3=document.getElementsByClassName('notice-title-li');
    var aDiv3=document.getElementsByClassName('pannal-notice');

    for(i=0;i<aLi3.length;i++){
        aLi3[i].index=i;
        aLi3[i].onclick=function(){
            for(j=0;j<aDiv3.length;j++){
               // aP[j].className=" ";
                aDiv3[j].className="pannal-notice pannal-notice-"+(j+1)+" hidden";

            }
           aDiv3[this.index].className="pannal-notice pannal-notice-"+(this.index+1)+" show";   
          
        }
    }
    