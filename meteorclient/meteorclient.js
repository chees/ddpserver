//var q42nl = DDP.connect("http://q42.nl");
//var Employees = new Meteor.Collection("Employees", q42nl);
//q42nl.subscribe("employees");

if (Meteor.isClient) {
  //window.employees = Employees;

  Template.hello.greeting = function () {
    return "Welcome to meteorclient.";
  };

  Template.hello.events({
    'click input': function () {
      // template data, if any, is available in 'this'
      Meteor.call('logCollection');
    }
  });
}

if (Meteor.isServer) {
  Meteor.methods({
    logCollection: function () {
      console.log('Names:');
      Names.find({}).forEach(function(n) {
        console.log(n.name);
      });
    }
  });

  var playConnection = DDP.connect('http://localhost:9000');
  var Names = new Meteor.Collection("names", playConnection);
  playConnection.subscribe("names");

  Meteor.startup(function () {
    // code to run on server at startup
  });
}
