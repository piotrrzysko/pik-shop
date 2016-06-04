angular.module('app.signIn', [
    'ui.router'
]).controller('SignInController', ['$scope', 'AuthenticationService', 'toastr', SignInController]);

function SignInController($scope, AuthenticationService, toastr) {
    $scope.credentials = {
        email: "",
        password: ""
    };

    $scope.signIn = function () {
        AuthenticationService.signIn($scope.credentials.email, $scope.credentials.password).then(function () {
            toastr.success("Zalogowano")
        }, function () {
            toastr.error("Niepoprawne dane logowania");
        });
    };
}