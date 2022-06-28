function [ib,b,sigma] = Imagebruite(img, SNR)
    % calcul de Pi
    Pi = sum(sum(img.^2)) / (size(img,1)*size(img,2));
    
    % calcul de Pb 
    Pb = Pi ./ (10^(SNR/10));
    sigma  =  sqrt(Pb);
    b = sigma * randn(size(img));
    ib = img + b; 
end