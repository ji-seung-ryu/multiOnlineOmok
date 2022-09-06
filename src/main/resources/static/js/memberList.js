document.addEventListener('click',function(e){
    if(e.target && e.target.id== 'playBtn'){
		if (e.target.className == username) alert('can not play alone!');
		else{
			inviteFight(e.target.className);
		}
    }
 });
 
