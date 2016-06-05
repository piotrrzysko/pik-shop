angular.module('app.profile.history', [
        'ui.router',
        'UsersService'
    ])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('profile.history', {
            url: '/history',
            views: {
                'profile@profile': {
                    controller: 'ProfileHistoryCtrl',
                    templateUrl: '/app/components/profile/history/history.html'
                }
            },
            resolve: {
                userOrders: ['UsersService', function (UsersService) {
                    return UsersService.getUserOrders(1);
                }]
            }
        });
    }])
    .controller('ProfileHistoryCtrl', ['$scope', 'userOrders', function ($scope, userOrders) {
        $scope.orders = userOrders;
    }]);