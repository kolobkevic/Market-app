angular.module('market-front').controller('storeController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {minPrice: $scope.minPrice, maxPrice: $scope.maxPrice, p: pageIndex},
        }).then(function (response) {
            console.log(response);
            $scope.productsListPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsListPage.totalPages);
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

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadProducts();
});