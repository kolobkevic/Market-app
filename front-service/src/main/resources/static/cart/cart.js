angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';

    $scope.loadCart = function () {
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId,
            method: 'GET',
        }).then(function (response) {
            console.log(response);
            $scope.cart = response.data;
        });
    };

    $scope.deleteFromCart = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId + '/delete/' + productId,
            method: 'GET',
        }).then(function successCallback(response) {
            console.log(response);
            alert('Продукт успешно удален');
            $location.path('/cart');
            $scope.loadCart();
        });
    };

    $scope.createOrder = function () {
        $http({
            url: contextPath + 'api/v1/orders',
            method: 'GET',
            data: $scope.orderDetails
        }).then(function (response) {
            console.log(response);
            $scope.loadCart();
            $scope.orderDetails = null
        });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId + '/clear')
            .then(function (response) {
                console.log(response);
                $scope.loadCart();
            });
    }

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.loadCart();
});