angular.module('app.admin-profile.dashboard', [
        'ui.router'
    ])
    .controller('AdminProfileDashboardCtrl', ['$scope', '$state', function ($scope, $state) {
        console.log("admin-dash");
        $scope.currentUser = {
            firstName: "Tomasz Tomaszewski",
            email: "tomek@tomek.pl",
            phoneNumber: 123456789,
            address: "Radom"
        };
    }]);