# JOURNAL-IA — Mini-TP 3

- Diagnostic de l'IA  : correct. La ligne 29 de MainActivity.kt est bien la bonne car findViewById y est appele avec un ID de bouton. La cause est exacte : NullPointerException parce que findViewById retourne null, le bouton n'existe pas dans le layout. La correction est la bonne : il faut changer btnPartage en btnPartager pour matcher le vrai ID dans activity_main.xml.

- Verification dans activity_main.xml : le bouton s'appelle btnPartager (id="@+id/btnPartager"), pas btnPartage. Donc le crash vient d'une faute de frappe dans le code qui appelle un ID qui n'existe pas dans le layout.

- Ce que j'en retiens : avant de chercher loin dans une stack trace, il faut toujours comparer les IDs du code avec ceux du layout XML. Le probleme est souvent un simple changement de lettre.
