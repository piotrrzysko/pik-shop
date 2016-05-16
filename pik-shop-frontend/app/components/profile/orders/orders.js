angular.module('app.profile.orders', [
        'ui.router'
    ])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('profile.orders', {
            url: '/orders',
            views: {
                'profile@profile': {
                    controller: 'ProfileOrdersCtrl',
                    templateUrl: '/app/components/profile/orders/orders.html'
                }
            }
        });
    }])
    .controller('ProfileOrdersCtrl', ['$scope', '$state', function ($scope, $state) {
        console.log("ProfileOrdersCtrl");
    }]);