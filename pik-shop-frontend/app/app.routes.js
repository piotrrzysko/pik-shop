(function () {
    'use strict';

    angular
        .module('app.routes', [])
        .config(['$stateProvider', '$urlRouterProvider', '$locationProvider', AppRoutes]);

    function AppRoutes($stateProvider, $urlRouterProvider, $locationProvider) {
        $locationProvider.html5Mode(false);
        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: '/app/components/home/home.html',
                controller: 'HomeController'
            })
            .state("admin", {
                abstract: true,
                url: "/admin",
                template: '<ui-view/>'
            })
            .state("admin.products", {
                abstract: true,
                url: "/products",
                template: '<ui-view/>',
                controller: 'AdminProductsController'
            })
            .state('admin.products.products-list', {
                url: '/list',
                templateUrl: '/app/components/product/adminProductsList.html',
                controller: 'AdminProductsListController'
            })
            .state('admin.products.add-product', {
                url: '/add',
                templateUrl: '/app/components/product/adminProductDetail.html',
                controller: 'AdminAddProductController'
            })
            .state('admin.products.view-product', {
                url: '/{productId}',
                templateUrl: '/app/components/product/productView.html',
                controller: 'ProductViewController'
            })
            .state('signIn', {
                url: '/signIn',
                templateUrl: '/app/components/signIn/signIn.html',
                controller: 'SignInController'
            })
            .state('signUp', {
                url: '/signUp',
                templateUrl: '/app/components/signUp/signUp.html',
                controller: 'SignUpController'
            });
    }
})();
