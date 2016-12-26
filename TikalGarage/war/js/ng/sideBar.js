app.service('sideBarService', [ '$http', '$q', function($http, $q) {
	this.getServicios = function() {
		$http.get("/servicio/getServiciosHoy").then(function(response) {
			console.log(response);
			d.resolve(response.data);
		}, function(response) {
		});
		return d.promise;

	}
} ]);

app.controller('sideBarController', [ '$scope', 'sideBarService', '$rootScope',
		'$location', '$timeout',
		function($scope, sideBarService, $rootScope, $location, $timeout) {

			$scope.serviciosActivos = $rootScope.serviciosHoy;
			$scope.goToView = function(id) {
				$location.path("/servicio/view/" + id);
			}

			$scope.$watch('actual', function() {
				if ($rootScope.actual.servicio.metadata.fechaInicio) {
					var hoy = new Date();
					var inicio = new Date($rootScope.actual.servicio.metadata.fechaInicio)
					var dif = hoy - inicio;
//					alert(dif);
					$scope.counter = dif;
//					$scope.dias= Math.floor($scope.counter / (1000 * 60 * 60 * 24));
//					$scope.horas= Math.floor($scope.counter / (1000 * 60 * 60))%24; 
//					$scope.minutos= Math.floor($scope.counter / (1000 * 60)) % (60); 
//					$scope.segundos= Math.floor($scope.counter / (1000))%(60); 
				}

			}, true);

			$scope.$watch('counter', function() {
//				if ($rootScope.actual.servicio.metadata.fechaInicio) {
//					var hoy = new Date();
//					var inicio = new Date($rootScope.actual.servicio.metadata.fechaInicio)
//					var dif = hoy - inicio;
//					alert(dif);
//					$scope.counter = dif;
					$scope.dias= Math.floor($scope.counter / (1000 * 60 * 60 * 24));
					$scope.horas= Math.floor($scope.counter / (1000 * 60 * 60))%24; 
					$scope.minutos= Math.floor($scope.counter / (1000 * 60)) % (60); 
					$scope.segundos= Math.floor($scope.counter / (1000))%(60); 
					
					document.getElementById("semaforo").className = "alert-success semGreen";
					document.getElementById("semaforo2").className = "alert-success semGreen";
					if($scope.dias>6 && $scope.dias<15){
						document.getElementById("semaforo").className = "alert-warning";
						document.getElementById("semaforo2").className = "alert-warning";
						
					}
					if($scope.dias>=15){
						document.getElementById("semaforo").className = "alert-danger";
						document.getElementById("semaforo2").className = "alert-danger";
					}
//				}

			}, true);
			
			$scope.onTimeout = function() {
				$scope.counter+=1000;
				mytimeout = $timeout($scope.onTimeout, 1000);
			}
			var mytimeout = $timeout($scope.onTimeout, 1000);

			$scope.stop = function() {
				$timeout.cancel(mytimeout);
			}
		} ]);