angular.module('app.signIn', [
    'ui.router'
]).controller('SignInController', ['$scope', 'AuthenticationService', 'toastr', 'UserStorageService', 'AppNotificationsService', SignInController]);

function SignInController($scope, AuthenticationService, toastr, UserStorageService, AppNotificationsService) {
    $scope.credentials = {
        email: "",
        password: ""
    };

    $scope.signIn = function () {
        AuthenticationService.signIn($scope.credentials.email, $scope.credentials.password).then(function () {
            UserStorageService.fetchLoggedUser().then(function () {
                AppNotificationsService.loginConfirmed();
                toastr.success("Zalogowano")
            });
        }, function () {
            toastr.error("Niepoprawne dane logowania");
        });
    };
}