'use strict';

/**
 * @ngdoc function
 * @name ebadgeFrontendApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the ebadgeFrontendApp
 */
angular.module('ebadgeFrontendApp')
  .controller('RequestCtrl', [ '$location',  'backEnd', function ($location, backEnd) {
    var ctrl = this;
    
    ctrl.selectedBuilding = { name: 'Select a building' };

    ctrl.model = {
      firstName: 'Enzo',
      lastName: 'Voort',
      email: 'antdim@gmail.com',
      mobile: '+32 475 123 456',
      idDocNumber: '123-34-567',
      validityIdDate: new Date(),
      dateOfBirth: new Date(2016, 4, 24),
      host: 'Jean-Claude Van Damme'
    }

    backEnd.call('GET', 'buildings').then(function(response) {
      ctrl.buildings = response._embedded.buildings;
    });

    ctrl.buildingSelected = function (building) {
      ctrl.selectedBuilding = building;
    }

    ctrl.submit = function () {
      backEnd.call('POST', 'visitor/register', ctrl.model).then(function(response) {
        $location.path('/status/ok');
      });
    }
  }]);
