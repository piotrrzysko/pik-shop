angular
  .module('app.product', ['ngTable'])
  .controller('AdminProductController', ['NgTableParams', '$scope', 'Product', AdminProductController]);

function AdminProductController(NgTableParams, $scope, Product) {
  var product = this;
  var stateIdToName = [{
    id: undefined,
    title: ""
  }, {
    id: "PUBLISHED",
    title: "Opublikowany"
  }, {
    id: "DELETED",
    title: "Usunięty"
  }, {
    id: "UNPUBLISHED",
    title: "Nieopublikowany"
  }, {
    id: "DISABLED",
    title: "Niedostępny"
  }];

  $scope.getProductStates = function() {
    return stateIdToName;
  };

  $scope.getProductStateName = function(id) {
    var displayState = "";
    stateIdToName.forEach(function(state) {
      if (state.id === id)
        displayState = state.title;
    });
    return displayState;
  }

  $scope.productTableParams = new NgTableParams({
    page: 1,
    count: 10,
  }, {
    filterDelay: 300,
    getData: function($defer, params) {
      Product.getProducts(params).then(function(response) {
        params.total(response.totalElements);
        $defer.resolve(response.content);
      });
    }
  });
}
