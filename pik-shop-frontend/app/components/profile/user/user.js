angular.module('app.profile.user', [
        'ui.router'
    ])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('profile.user', {
            url: '/orders',
            views: {
                'profile@profile': {
                    controller: 'ProfileUsersCtrl',
                    templateUrl: '/app/components/profile/user/user.html'
                }
            }
        });
    }])
    .controller('ProfileUsersCtrl', ['$scope', '$state', function ($scope, $state) {
        console.log("ProfileUsersCtrl");
    }]);