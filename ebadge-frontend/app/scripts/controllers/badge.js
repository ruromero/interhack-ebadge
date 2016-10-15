'use strict';

/**
 * @ngdoc function
 * @name ebadgeFrontendApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the ebadgeFrontendApp
 */
angular.module('ebadgeFrontendApp')
  .controller('BadgeCtrl', function ($location) {

  	this.model = {
  		firstName: 'Ruben',
  		lastName: 'Romero Montes',
  		email: 'Ruben.ROMERO-MONTES@ec.europa.eu',
  		mobile: '23423',
  		employeeNumber: '2343-23423',
  		validityIdDate: new Date()
  	};

  	this.submit = function() {
  		$location.path('/qrcode')
  	};

  });
