angular.module('app.profile.dashboard', [
        'ui.router'
    ])
    .controller('ProfileDashboardCtrl', ['$scope', '$state', function ($scope, $state) {
        console.log("dash");
        $scope.currentUser = {
            firstName: "Jan Kowalski",
            email: "jan@kowalski.pl",
            phoneNumber: 666666666,
            address: "Warszawa"
        };
    }]);