function mat = matriceHomogeneElementaire(type,axe,valeur)
    
    mat = zeros(4,4);
    mat(4,4) = 1;
    
    if type == 'r'
        switch axe
            case 'x'
                mat(1,1) = 1;
                mat(2,2) = cos(valeur);
                mat(2,3) = -sin(valeur);
                mat(3,2) = sin(valeur);
                mat(3,3) = cos(valeur);
            case 'y'
                mat(1,1) = cos(valeur);
                mat(1,3) = sin(valeur);
                mat(2,2) = 1;
                mat(3,1) = -sin(valeur);
                mat(3,3) = cos(valeur);
            case 'z'
                mat(1,1) = cos(valeur);
                mat(1,2) = -sin(valeur);
                mat(2,1) = sin(valeur);
                mat(2,2) = cos(valeur);
                mat(3,3) = 1;
        end
        return
         
    elseif type == 't'
        mat(1,1)= 1;
        mat(2,2)= 1;
        mat(3,3)= 1;
        switch axe
            case 'x'
                mat(1,4) = mat(1,4)+valeur;
            case 'y'
                mat(2,4) = mat(1,4)+valeur;
            case 'z'
                mat(3,4) = mat(1,4)+valeur;
        end
        return
    
end