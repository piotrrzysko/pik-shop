angular
    .module('app.home', [
        'app.signUp'
    ])
    .controller('HomeController', ['$scope', '$uibModal', HomeController]);

function HomeController($scope, $uibModal) {
    var home = this;
    home.openRegisterModal = function () {
        $uibModal.open({
            templateUrl: '/app/components/signUp/signUp.html',
            controller: 'SignUpController',
            animation: 'am-fade-and-scale',
            controllerAs: 'ctrl'
        });
        console.log("modalRegister");
    };

    home.openSignInModal = function () {
        $uibModal.open({
            templateUrl: '/app/components/signIn/signIn.html',
            controller: 'SignUpController',
            animation: 'am-fade-and-scale',
            controllerAs: 'ctrl'
        });
        console.log("modalSignIn");
    };


    $scope.sportImages = [{
        id: 1,
        text: "Hello",
        image: '/assets/img/img1.png'
    }];

    $scope.myInterval = 3000;
    $scope.noWrapSlides = false;
    $scope.active = 0;
    var slides = $scope.slides = [];
    var currIndex = 0;

    $scope.addSlide = function (i) {
        var newWidth = 600 + slides.length + 1;
        slides.push({
            image: 'assets/img/img' + i + '.png',
            text: ['opis produktu 1', 'Jeszcze ciekawszy opis produktu 2', 'No tego opisu to juz nic nie przebije! Opis produktu 3', 'I love that'][slides.length % 4],
            id: currIndex++
        });
    };


    for (var i = 0; i < 3; i++) {
        $scope.addSlide(i + 1);
    }


    $scope.availableSearchParams = [
        {key: "name", name: "nazwa", placeholder: "Nazwa..."},
        {key: "price", name: "cena", placeholder: "Cena..."},
        {key: "quantity", name: "ilość", placeholder: "Ilość..."},
    ];

    $scope.searchParams = {};

}