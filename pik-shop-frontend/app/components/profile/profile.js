angular.module('app.profile', [
        'ui.router',
        'app.profile.history',
        'app.profile.orders',
        'app.profile.sells',
        'app.profile.user'
    ])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('profile', {
            url: '/profile',
            views: {
                '@': {
                    controller: 'ProfileCtrl',
                    templateUrl: '/app/components/profile/profile.html'
                },
                'profile@profile': {
                    controller: 'ProfileDashboardCtrl',
                    templateUrl: '/app/components/profile/dashboard/dashboard.html'
                }
            }
        });
    }])
    .controller('ProfileCtrl', ['$scope', '$state', function ($scope, $state) {
        $scope.isProfileDashboardState = function () {
            return $state.current.name === 'profile';
        }
    }]);