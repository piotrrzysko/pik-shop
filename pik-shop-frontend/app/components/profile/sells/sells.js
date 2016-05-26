angular.module('app.profile.sells', [
        'ui.router'
    ])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('profile.sells', {
            url: '/sells',
            views: {
                'profile@profile': {
                    controller: 'ProfileSellsCtrl',
                    templateUrl: '/app/components/profile/sells/sells.html'
                }
            }
        });
    }])
    .controller('ProfileSellsCtrl', ['$scope', '$state', function ($scope, $state) {
        console.log("ProfileSellsCtrl");
        
    }]);