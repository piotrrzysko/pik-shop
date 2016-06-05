angular
    .module('UsersService', ['restangular'])
    .factory('UsersService', ['Restangular', UsersService]);

function UsersService(Restangular){

    return {
        getUsersList: getUsersList,
        getUserOrders: getUserOrders
    };

    // function getUsersList() {
    //     return Restangular.one('users').get();
    // }

    function getUsersList(params) {
        var sortingCol = Object.keys(params.sorting())[0];
        var request = {
            page: params.page() - 1,
            size: params.count(),
            id: params.filter().id,
            first_name: params.filter().first_name,
            last_name: params.filter().last_name,
            email: params.filter().email,
            sortCol: sortingCol,
            direction: params.sorting()[sortingCol]
        };

        return Restangular.one('users').get(request);
    }

    function getUserOrders(userId) {
        return Restangular.one('users/'+userId+'/orders').get();
    }
}