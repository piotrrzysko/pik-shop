angular
  .module('app.routes', [])
  .config(['$stateProvider', '$urlRouterProvider', '$locationProvider', AppRoutes]);

function AppRoutes($stateProvider, $urlRouterProvider, $locationProvider) {
  $locationProvider.html5Mode(true);
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
    .state('admin.product', {
      url: '/product',
      templateUrl: '/app/components/product/adminProductsList.html',
      controller: 'AdminProductController'
    });
}
