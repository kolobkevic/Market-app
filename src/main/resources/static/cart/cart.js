angular.module('market-front').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadCart = function () {
        $http({
            url: contextPath + 'api/v1/cart',
            method: 'GET',
        }).then(function (response) {
            console.log(response);
            $scope.productsInCart = response.data;
        });
    };

    $scope.deleteFromCart = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/delete/' + productId,
            method: 'GET',
        }).then(function successCallback(response) {
            alert('Продукт успешно удален');
            $location.path('/cart');
            $scope.loadCart();
        });
    };

    $scope.loadCart();
});