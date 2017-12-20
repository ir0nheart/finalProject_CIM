angular.module(['carOwner'], ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module(['carOwner']).controller('DatepickerCtrl', function ($scope) {
	  $scope.times=[{'time':"8:00 AM",'id':"1",'hour':8},{'time':"9:00 AM",'id':"2",'hour':9},{'time':"10:00 AM",'id':"3",'hour':10},{'time':"11:00 AM",'id':"4",'hour':11},
		  			{'time':"12:00 PM",'id':"5",'hour':12},{'time':"1:00 PM",'id':"6",'hour':13},{'time':"2:00 PM",'id':"7",'hour':14},{'time':"3:00 PM",'id':"8",'hour':15},
			{'time':"4:00 PM",'id':"9",'hour':16},
				{'time':"5:00 PM",'id':"10",'hour':17}];
	$scope.addRow=function(){
	$scope.times.push({'time':$scope.time});
	$scope.time='';	
	};
	
  $scope.today = function() {
    $scope.dt = new Date();
  };
  $scope.today();

  $scope.clear = function() {
    $scope.dt = null;
  };

  $scope.options = {
    customClass: getDayClass,
    minDate:$scope.toggleMin,
    showWeeks: true,
    dateDisabled:disabled  // To disable weekend selection
  };

  // Disable weekend selection
  function disabled(data) {
    var date = data.date,
      mode = data.mode;
    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
  }
  


  $scope.toggleMin = function() {
    $scope.options.minDate = $scope.options.minDate ? null : new Date();
  };

  $scope.toggleMin();

  $scope.setDate = function(year, month, day) {
    $scope.dt = new Date(year, month, day);
  };

  var tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  var afterTomorrow = new Date(tomorrow);
  afterTomorrow.setDate(tomorrow.getDate() + 1);
  $scope.events = [
    {
      date: tomorrow,
      status: 'full'
    },
    {
      date: afterTomorrow,
      status: 'partially'
    }
  ];

  function getDayClass(data) {
    var date = data.date,
      mode = data.mode;
    if (mode === 'day') {
      var dayToCheck = new Date(date).setHours(0,0,0,0);

      for (var i = 0; i < $scope.events.length; i++) {
        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

        if (dayToCheck === currentDay) {
          return $scope.events[i].status;
        }
      }
    }

    return '';
  }
});