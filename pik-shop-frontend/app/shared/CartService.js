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
        getPaymentTypes: getPaymentTypes
    };

    function addOrderItem(productId, amount) {
        var orderItem = {
            productId: productId,
            amount: amount
        };
        return Restangular.all('orders').customPOST(orderItem);
    }

    function getOrder() {
        return Restangular.one('orders').get();
    }

    function deleteOrderItem(itemId) {
        return Restangular.one('orders', itemId).remove();
    }

    function updateOrder(order) {
        return Restangular.all('orders/update').post(order);
    }

    function confirmOrder() {
        return Restangular.all('orders/setStatusConfirmed').post();
    }

    function getDeliveryFormTypes() {
        return Restangular.one('orders/deliveryFormTypes').get();
    }

    function getPaymentTypes() {
        return Restangular.one('orders/paymentTypes').get();
    }
}
