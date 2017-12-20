
var myVar = "[{title:'Burak Demiryurek,6LM294',start:'2017-12-13T10:00:00',end:'2017-12-13T11:00:00',url:'checkAppointment?id=3'}]";
$(document).ready(function(){
		
	
    	 $('#calendar').fullCalendar({
    		 
    		 header:{
    			 left: 'prev,next,today',
    			 center: 'title',
    			 right:'month,agendaWeek,agendaDay'
    		 },
    		 defaultView:'agendaWeek',
    	     weekends:false,
    	     weekNumbers:true,
    	     close: 'glyphicon-remove',
    	     prev: 'glyphicon-chevron-left',
    	     next: 'glyphicon-chevron-right',
    	     prevYear: 'glyphicon-backward',
    	     nextYear: 'glyphicon-forward',
    	     businessHours:  // specify an array instead
    	    	    {
    	    	        dow: [ 1, 2, 3,4,5 ], // Monday, Tuesday, Wednesday
    	    	        start: '08:00', // 8am
    	    	        end: '18:00' // 6pm
    	    	    }
    	    	,
    	     googleCalendarApiKey: 'AIzaSyDlzrXxpEP1yW5LKO0Ak4zxNciJuZefbS4',
    	     eventSources: [
    	            {
    	                googleCalendarId: '0s44tqv54lrt7kgv32oj1490ig@group.calendar.google.com',
    	                
    	                color: 'yellow',   // an option!
    	                textColor: 'black' // an option!
    	            },
    	            
    	           
    	         
    	         
    	           
    	  
    	        ],
    	        
    	        events:[
  	        	  {title:'Burak Demiryurek,6LM294', start:'2017-12-13T10:00:00', end:'2017-12-13T11:00:00',url:'checkAppointment?id=3'}
  	          ],
  	        		
    	       
    	        
    	        
//    	        events:function(start,end,timezone,callback){
//    	        	var xmlhttp;
//    	        	var myEvent=[];
//    	        	if(window.XMLHttpRequest){
//    	        		xmlhttp=new XMLHttpRequest();
//    	        	}else{
//    	        		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//
//    	        	}
//    	        	xmlhttp.onreadystatechange = function(){
//    	        		if(xmlhttp.readyState==4 && xmlhttp.status==200)
//    	        			//myEvent.push(this.responseText);
//    	        			myEvent.push(this.responseText);
//    	        		callback(myEvent)
//    	        	}
//    	        	xmlhttp.open("GET","getCalendar",true);
//    	        	xmlhttp.send();
//    	        	
//    	        	
//    	        	
//    	        	},color: 'blue',   // an option!
//    	        textColor: 'black' // an option!
    	    
    	     
   	     
    	    })
    }





);




