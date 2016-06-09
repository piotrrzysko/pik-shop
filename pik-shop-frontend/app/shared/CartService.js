angular
    .module('CartService', ['restangular'])
    .factory('Cart', ['Restangular', CartService]);

function CartService(Restangular) {

    return {
        addOrderItem: addOrderItem,
        getOrder: getOrder,
        deleteOrderItem: deleteOrderItem,
        confirmOrder: confirmOrder,
        updateOrder: updateOrder,
        getDeliveryFormTypes: getDeliveryFormTypes,
        getPaymentTypes: getPaymentTypes,
        linkOrderWithUser: linkOrderWithUser
    };

    function addOrderItem(productId, amount) {
        var orderItem = {
            productId: productId,
            amount: amount
        };
        return Restangular.all('public/orders').customPOST(orderItem);
    }

    function getOrder() {
        return Restangular.one('public/orders').get();
    }

    function deleteOrderItem(itemId) {
        return Restangular.one('public/orders', itemId).remove();
    }

    function updateOrder(order) {
        return Restangular.all('public/orders/update').post(order);
    }

    function confirmOrder() {
        return Restangular.all('public/orders/setStatusConfirmed').post();
    }

    function getDeliveryFormTypes() {
        return Restangular.one('public/orders/deliveryFormTypes').get();
    }

    function getPaymentTypes() {
        return Restangular.one('public/orders/paymentTypes').get();
    }

    function linkOrderWithUser() {
        return Restangular.all('public/orders/linkWithUser').post();
    }
}
