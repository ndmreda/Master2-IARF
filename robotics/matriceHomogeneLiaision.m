function mat=matriceHomogeneLiaision(a, alpha, r, teta)

    transElem_a_x = matriceHomogeneElementaire('t', 'x', a);
    rotElem_alpha_x = matriceHomogeneElementaire('r', 'x', alpha);
    transElem_r_z = matriceHomogeneElementaire('t', 'z', r);
    rotElem_teta_z =matriceHomogeneElementaire('r', 'z', teta);
    
    mat = transElem_a_x * rotElem_alpha_x  * transElem_r_z * rotElem_teta_z ;

end
