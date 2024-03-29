function validateEmail(addr,man,db) {
if (addr == '' && man) {
   if (db) alert('email address is mandatory');
   return false;
}
if (addr == '') return true;
var invalidChars = '\/\'\\ ";:?!()[]\{\}^|';
for (i=0; i<invalidChars.length; i++) {
   if (addr.indexOf(invalidChars.charAt(i),0) > -1) {
      if (db) alert('email address contains invalid characters');
      return false;
   }
}
for (i=0; i<addr.length; i++) {
   if (addr.charCodeAt(i)>127) {
      if (db) alert("email address contains non ascii characters.");
      return false;
   }
}
var atPos = addr.indexOf('@',0);
if (atPos == -1) {
   if (db) alert('email address must contain an @');
   return false;
}
if (atPos == 0) {
   if (db) alert('email address must not start with @');
   return false;
}
if (addr.indexOf('@', atPos + 1) > - 1) {
   if (db) alert('email address must contain only one @');
   return false;
}
if (addr.indexOf('.', atPos) == -1) {
   if (db) alert('email address must contain a period in the domain name');
   return false;
}
if (addr.indexOf('@.',0) != -1) {
   if (db) alert('period must not immediately follow @ in email address');
   return false;
}
if (addr.indexOf('.@',0) != -1){
   if (db) alert('period must not immediately precede @ in email address');
   return false;
}
if (addr.indexOf('..',0) != -1) {
   if (db) alert('two periods must not be adjacent in email address');
   return false;
}
var suffix = addr.substring(addr.lastIndexOf('.')+1);
if (suffix.length != 2 && suffix != 'com' && suffix != 'net' && suffix != 'org' && suffix != 'edu' && suffix != 'int' && suffix != 'mil' && suffix != 'gov' & suffix != 'arpa' && suffix != 'biz' && suffix != 'aero' && suffix != 'name' && suffix != 'coop' && suffix != 'info' && suffix != 'pro' && suffix != 'museum') {
   if (db) alert('invalid primary domain in email address');
   return false;
}
return true;
}