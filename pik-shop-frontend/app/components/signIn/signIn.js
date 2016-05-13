angular.
module('app.signIn', [
    'ui.router'
]).controller('SignInController',['$scope', SignInController]);

function SignInController($scope) {
    console.log("hello");
}