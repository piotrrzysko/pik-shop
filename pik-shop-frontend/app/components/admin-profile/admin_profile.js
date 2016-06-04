angular.module('app.admin-profile', [
        'ui.router',
        'app.admin-profile.dashboard',
        'app.admin-profile.userslist'
    ])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('admin-profile', {
            url: '/admin-profile',
            views: {
                '@': {
                    controller: 'Admin-ProfileCtrl',
                    templateUrl: '/app/components/admin-profile/admin-profile.html'
                },
                'admin-profile@admin-profile': {
                    controller: 'AdminProfileDashboardCtrl',
                    templateUrl: '/app/components/admin-profile/dashboard/dashboard.html'
                }
            }
        });
    }])
    .controller('Admin-ProfileCtrl', ['$scope', '$state', function ($scope, $state) {
        $scope.isAdminProfileDashboardState = function () {
            return $state.current.name === 'admin-profile';
        }
    }]);