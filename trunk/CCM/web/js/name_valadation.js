// Email Validation Javascript
// copyright 23rd March 2003, by Stephen Chapman, Felgall Pty Ltd

// You have permission to copy and use this javascript provided that
// the content of the script is not changed in any way.

function validatename(addr,man,db) {
if (addr == '' && man) {
   if (db) alert('name is mandatory');
   return false;
}
if (addr == '') return true;

var invalidChars = '1234567890';
for (i=0; i<invalidChars.length; i++) {
   if (addr.indexOf(invalidChars.charAt(i),0) > -1) {
      if (db) alert('name contains invalid characters');
      return false;
   }
}
return true;
}
