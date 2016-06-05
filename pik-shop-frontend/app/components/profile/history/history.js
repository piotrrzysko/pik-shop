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
            }
        });
    }])
    .controller('ProfileHistoryCtrl', ['NgTableParams','$scope', 'UsersService', function (NgTableParams,$scope, UsersService) {
        console.log("ProfileHistoryCtrl");

        $scope.historyTableParams = new NgTableParams({
            page: 1,
            count: 10
        }, {
            filterDelay: 300,
            getData: function ($defer, params) {
                //TODO zmienic wartosc "1" na userId - dostepne po zalogowaniu, 1 jest testowa
                UsersService.getUserOrders(params,1).then(function (response) {
                    params.total(response.totalElements);
                    $defer.resolve(response.content);
                });
            }
        });

    }]);