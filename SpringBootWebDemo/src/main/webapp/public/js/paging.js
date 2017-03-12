				var tbody=document.getElementById('tbody');
				var atr=tbody.getElementsByTagName('tr');
				var pages=Math.ceil(atr.length/12);
				
				var paging=document.getElementById('paging');
				for(var i=0;i<atr.length;i++){
					atr[i].firstElementChild.innerHTML=i+1;

				}
				//分页，添加页码
				for(var i=1;i<=pages;i++){

					// a[0].style.backgroundColor="";		
					// a[0].style.color="#252525";

					if(pages>6){
					
						for(var i=1;i<=5;i++){
							var nodeA=document.createElement("a");
							var textNode=document.createTextNode(i);
							nodeA.appendChild(textNode);
							paging.appendChild(nodeA);

						}

						var a=paging.getElementsByTagName('a');
						a[0].style.backgroundColor="#fa3e3e";
						a[0].style.color="#ffffff";
						

						var nodeS=document.createElement("span");
						var ellipsis=document.createTextNode('...');
						nodeS.appendChild(ellipsis);
						paging.appendChild(nodeS);

						var nodeAEnd=document.createElement("a");
						var textEnd=document.createTextNode(pages);
						nodeAEnd.appendChild(textEnd);
						paging.appendChild(nodeAEnd);
						break;					
					}
					if(pages<=6){
						for(var i=1;i<=pages;i++){
							var nodeA=document.createElement("a");
							var textNode=document.createTextNode(i);
							nodeA.appendChild(textNode);
							paging.appendChild(nodeA);

						}
						var a=paging.getElementsByTagName('a');
						a[0].style.backgroundColor="#fa3e3e";
						a[0].style.color="#ffffff";
						for(var i=12;i<atr.length;i++){
							atr[i].style.display='none';
						}
						
					}
				}
				
				
				function page(){
					var a=paging.getElementsByTagName("a");
					
					for(var j=0;j<a.length;j++){
						
						a[j].onclick=function(){
							var num=parseInt(this.innerHTML);
							if(num<=5){
								if(pages>6){
									paging.innerHTML='';
									var num=parseInt(num);
									for(var i=1;i<=5;i++){
										var nodeA=document.createElement("a");
										var textNode=document.createTextNode(i);
										nodeA.appendChild(textNode);
										paging.appendChild(nodeA);
									}
									var nodeS=document.createElement("span");
									var ellipsis=document.createTextNode('...');
									nodeS.appendChild(ellipsis);
									paging.appendChild(nodeS);

									var nodeAEnd=document.createElement("a");
									var textEnd=document.createTextNode(pages);
									nodeAEnd.appendChild(textEnd);
									paging.appendChild(nodeAEnd);


									var index=parseInt(num-1);
									a[index].style.backgroundColor="#fa3e3e";		
									a[index].style.color="#ffffff";
								}
								else{
									for(var i=0;i<a.length;i++){
										a[i].style.backgroundColor="";		
										a[i].style.color="#252525";
									}
									var index=parseInt(num-1);
									a[index].style.backgroundColor="#fa3e3e";		
									a[index].style.color="#ffffff";
								}
								
							}
							if(num>=5&&num<=pages-5){
								paging.innerHTML='';
								var nodeAFirst=document.createElement('a');
								var textFirst=document.createTextNode('1');
								nodeAFirst.appendChild(textFirst);
								paging.appendChild(nodeAFirst);


								var nodeS=document.createElement("span");
								var ellipsis=document.createTextNode('...');
								nodeS.appendChild(ellipsis);
								paging.appendChild(nodeS);
								
								
							
								for(var i=num-2;i<=num+2;i++){
									var nodeA=document.createElement("a");
									var textNode=document.createTextNode(i);
									nodeA.appendChild(textNode);
									paging.appendChild(nodeA);
								}

								var nodeS=document.createElement("span");
								var ellipsis=document.createTextNode('...');
								nodeS.appendChild(ellipsis);
								paging.appendChild(nodeS);

								var nodeAEnd=document.createElement("a");
								var textEnd=document.createTextNode(pages);
								nodeAEnd.appendChild(textEnd);
								paging.appendChild(nodeAEnd);

								a[3].style.backgroundColor="#fa3e3e";		
								a[3].style.color="#ffffff";	
							}
							
							if(num>pages-5){
								if(pages>6){
									paging.innerHTML='';
									var nodeAFirst=document.createElement('a');
									var textFirst=document.createTextNode('1');
									nodeAFirst.appendChild(textFirst);
									paging.appendChild(nodeAFirst);


									var nodeS=document.createElement("span");
									var ellipsis=document.createTextNode('...');
									nodeS.appendChild(ellipsis);
									paging.appendChild(nodeS);	

									
									for(var i=num-5+pages-num;i<=pages;i++){
										var nodeA=document.createElement("a");
										var textNode=document.createTextNode(i);
										nodeA.appendChild(textNode);
										paging.appendChild(nodeA);
									}
									
									var index=parseInt(num-1);
									a[7-pages+index].style.backgroundColor="#fa3e3e";		
									a[7-pages+index].style.color="#ffffff";
								}
								if(num==6){
									for(var i=0;i<a.length;i++){
										a[i].style.backgroundColor="";		
										a[i].style.color="#252525";
									}
								
									a[5].style.backgroundColor="#fa3e3e";		
									a[5].style.color="#ffffff";
								}
							}			
							numTr=atr.length;
							if(num===1){
								for(var i=(num-1)*12;i<num*12;i++){
									
									atr[i].style.display='table-row';

								}
								for(var i=12;i<numTr;i++){
									atr[i].style.display='none';

								}
							}
							if(num>1&&num<numTr){
								for(var i=0;i<(num-1)*12;i++){
									atr[i].style.display='none';

								}
								for(var i=(num-1)*12;i<num*12;i++){

									atr[i].style.display='table-row';


								}
								for(var i=num*12;i<numTr;i++){
									atr[i].style.display='none';

								}
							}
							if(num===pages){
								for(var i=0;i<(num-1)*12;i++){
									atr[i].style.display='none';

								}
								for(var i=(num-1)*12;i<numTr;i++){
									atr[i].style.display='table-row';

								}
							}
							page();
						}
						
					}
				}
				page();
				