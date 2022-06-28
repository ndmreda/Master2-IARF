function mat=matriceHomogeneValidation(X)
  
  trans_x = matriceHomogeneElementaire('t', 'x', X(1));
  trans_y = matriceHomogeneElementaire('t', 'y', X(2));
  trans_z = matriceHomogeneElementaire('t', 'z', X(3));
  rotation_x = matriceHomogeneElementaire('r','x',deg2rad(X(4)));
  rotation_y = matriceHomogeneElementaire('r','y',deg2rad(X(5)));
  rotation_z = matriceHomogeneElementaire('r','z',deg2rad(X(6)));
  
  mat = trans_x * trans_y * trans_z * rotation_x * rotation_y * rotation_z;
end

  