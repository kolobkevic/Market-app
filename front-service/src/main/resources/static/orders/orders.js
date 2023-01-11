angular.module('market-front').controller('ordersController', function ($scope, $http) {

    $scope.loadOrders = function () {
        $http.get('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                console.log(response);
                $scope.MyOrders = response.data;
            });
    }

    $scope.loadOrders();
});