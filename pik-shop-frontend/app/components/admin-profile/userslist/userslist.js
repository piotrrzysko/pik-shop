angular.module('app.admin-profile.userslist', [
        'ui.router',
        'UsersService'
    ])
    .config(['$stateProvider', function config($stateProvider) {
        $stateProvider.state('admin-profile.userslist', {
            url: '/userslist',
            views: {
                'admin-profile@admin-profile': {
                    controller: 'Admin-ProfileUsersListCtrl',
                    templateUrl: '/app/components/admin-profile/userslist/userslist.html'
                }
            }
        });
    }])
    .controller('Admin-ProfileUsersListCtrl', ['NgTableParams', '$scope','UsersService', function (NgTableParams,$scope, UsersService) {
        console.log("Admin-ProfileUsersListCtrl");
            // $scope.users = order;
        // UsersService.getUsersList().then(function(order) {
        // }, function() {
        //     toastr.error('Wystąpił błąd podczas wczytywania zawartości koszyka');
        // });
        // console.log($scope.users);

        

        $scope.userTableParams = new NgTableParams({
            page: 1,
            count: 10
        }, {
            filterDelay: 300,
            getData: function ($defer, params) {
                UsersService.getUsersList(params).then(function (response) {
                    params.total(response.totalElements);
                    $defer.resolve(response.content);
                });
            }
        })
    }]);