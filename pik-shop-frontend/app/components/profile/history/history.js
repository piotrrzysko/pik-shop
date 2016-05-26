angular.module('app.profile.history', [
        'ui.router'
    ])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('profile.history', {
            url: '/history',
            views: {
                'profile@profile': {
                    controller: 'ProfileHistoryCtrl',
                    templateUrl: '/app/components/profile/history/history.html'
                }
            }
        });
    }])
    .controller('ProfileHistoryCtrl', ['$scope', '$state', function ($scope, $state) {
        console.log("ProfileHistoryCtrl");
    }]);