(function () {
    'use strict';

    angular
        .module('app.routes', [])
        .config(['$stateProvider', '$urlRouterProvider', '$locationProvider', AppRoutes]);

    function AppRoutes($stateProvider, $urlRouterProvider, $locationProvider) {
        $locationProvider.html5Mode(false);
        $urlRouterProvider.otherwise('/');

        $stateProvider
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
            .state("products", {
                abstract: true,
                url: "/products",
                template: '<ui-view/>'
            })
            .state('products.view-product', {
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
            })
            .state("cart", {
                abstract: true,
                url: "/cart",
                template: '<ui-view/>',
                controller: 'CartController'
            })
            .state("cart.items-list", {
                url: "/",
                templateUrl: '/app/components/cart/cartItemsList.html',
                controller: 'CartItemsListController'
            })
            .state("cart.delivery-payment", {
                url: "/",
                templateUrl: '/app/components/cart/cartDeliveryAndPayment.html',
                controller: 'CartDeliveryAndPaymentController'
            })
            .state("cart.summary", {
                url: "/",
                templateUrl: '/app/components/cart/cartSummary.html',
                controller: 'CartSummaryController'
            });
    }
})();
