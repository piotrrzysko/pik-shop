angular.
module('app.signUp', [
    'ui.router'
]).controller('SignUpController',['$scope', SignUpController]);

function SignUpController($scope) {
    console.log("hello1");
}