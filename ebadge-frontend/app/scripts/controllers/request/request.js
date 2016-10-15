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

    this.model = {
      firstName: 'Enzo',
      lastName: 'Voort',
      email: 'enzo.voort@gmail.com',
      mobile: '+32 475 123 456',
      idDocNumber: '123-34-567',
      validityIdDate: new Date(),
      dateOfBirth: new Date(2016, 4, 24),
      host: 'Jean-Claude Van Damme'
    }

    this.buildings = backEnd.call('GET', 'buildings').then(function (response) {
      //console.log(response._embedded.buildings);
    });

    this.buildingSelected = function (building) {
      this.selectedBuilding = building;
    }

    this.submit = function () {
      console.log('submitted', this.model);

      //backEnd.call('POST', 'register', this.model);
    }
  }]);
