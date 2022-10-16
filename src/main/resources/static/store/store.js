angular.module('market-front').controller('storeController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',

        }).then(function (response) {
            console.log(response);
            $scope.productsList = response.data;
        });
    };

    $scope.loadFilteredProducts = function () {
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {minPrice: $scope.minPrice, maxPrice: $scope.maxPrice},
        }).then(function (response) {
                console.log(response);
                $scope.productsList = response.data;
            });
    };


    $scope.navToEditProductPage = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.deleteProduct = function (productId) {
        $http({
            url: contextPath + 'api/v1/products/delete/' + productId,
            method: 'DELETE',

        }).then(function successCallback(response) {
            alert('Продукт успешно удален');
            $location.path('/store');
            $scope.loadProducts();
        }, function failureCallback(response) {
            alert(response.data.messages);
        });
    };

    $scope.loadProducts();
});