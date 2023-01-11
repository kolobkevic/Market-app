angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
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
            console.log(response)
            alert('Продукт успешно удален');
            $location.path('/store');
            $scope.loadProducts();
        }, function failureCallback(response) {
            console.log(response);
            alert('Недостаточно прав для удаления');
        });
    };

    $scope.addToCart = function (productId) {
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.springWebGuestCartId + '/add/' + productId,
            method: 'GET',
        }).then(function successCallback(response) {
            alert('Продукт добавлен в корзину');
            console.log(response);
            // $location.path('/store');
            // $scope.loadProducts();
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