//var q42nl = DDP.connect("http://q42.nl");
//var Employees = new Meteor.Collection("Employees", q42nl);
//q42nl.subscribe("employees");

var play = DDP.connect('http://localhost:9000');
var Bla = new Meteor.Collection("bla", play);
play.subscribe("bla");

if (Meteor.isClient) {
  window.employees = Employees;

  Template.hello.greeting = function () {
    return "Welcome to meteorclient.";
  };

  Template.hello.events({
    'click input': function () {
      // template data, if any, is available in 'this'
      if (typeof console !== 'undefined')
        console.log("You pressed the button");
    }
  });
}

if (Meteor.isServer) {
  Meteor.startup(function () {
    // code to run on server at startup
  });
}
