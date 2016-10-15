'use strict';

/**
 * @ngdoc function
 * @name ebadgeFrontendApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the ebadgeFrontendApp
 */
angular.module('ebadgeFrontendApp')
  .controller('RequestCtrl', [ 'backEnd', function (backEnd) {
    this.selectedBuilding = { name: 'Select a building' };

    this.buildings = [
      { name: "111" },
      { name: "222" },
      { name: "333" },
      { name: "444" },
      { name: "555" },
      { name: "666" },
    ];
    /*
    this.buildingList = backEnd.call('GET', 'buildingList').then(function (buildingList) {
      console.log(buildingList);
    });
    */

    this.buildingSelected = function (building) {
      this.selectedBuilding = building;
    }

    this.submit = function () {
      console.log('submitted', this.model);

      backEnd.call('POST', 'register', this.model);
    }
  }]);
