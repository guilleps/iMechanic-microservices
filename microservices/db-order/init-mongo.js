db.adminCommand(
   {
      setFeatureCompatibilityVersion: "4.2",
      confirm: true
   }
)

db.createUser({
  user: 'root',
  pwd: '129837465lg',
  roles: [{ role: 'root', db: 'admin' }]
});


db.adminCommand( { getParameter: 1, featureCompatibilityVersion: 1 } )