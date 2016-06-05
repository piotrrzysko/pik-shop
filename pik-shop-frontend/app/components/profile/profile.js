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
            },
            resolve: {
                currentUser: ['UserStorageService', function (UserStorageService) {
                    if (UserStorageService.hasLoginCookie()) {
                        return UserStorageService.fetchLoggedUser();
                    }
                    return UserStorageService.getCurrentUser();
                }]
            }
        });
    }])
    .controller('ProfileCtrl', ['$scope', '$state', 'currentUser', function ($scope, $state, currentUser) {

        $scope.currentUser = currentUser;
        $scope.isProfileDashboardState = function () {
            return $state.current.name === 'profile';
        }
    }]);