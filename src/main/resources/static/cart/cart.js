angular.module('market-front').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadCart = function () {
        $http({
            url: contextPath + 'api/v1/cart',
            method: 'GET',
        }).then(function (response) {
            console.log(response);
            $scope.Cart = response.data;
        });
    };

    $scope.deleteFromCart = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/delete/' + productId,
            method: 'DELETE',
        }).then(function successCallback(response) {
            alert('Продукт успешно удален');
            $location.path('/cart');
            $scope.loadCart();
        });
    };

    $scope.createOrder = function () {
        $http({
            url: contextPath + 'api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            $scope.loadCart();
            $scope.orderDetails = null
        });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + 'api/v1/cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.loadCart();
});