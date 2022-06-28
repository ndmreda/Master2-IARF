%1)

img = imread("lenna.tif");

img=double(img);
figure();
imagesc(img);
colormap(gray);
colorbar; 
% a) soit Pb puissance du bruit
%  on a SNR = Pi / Pb donc Pb = Pi / SNR
%b)
SNR = 0;
[ib,bruit] = genererimage(img,SNR);

figure();
imagesc(ib);
colormap(gray);
colorbar; 

%c) plus on a le SNR petit plus l'image est trop bruité
%plus c'est grand plus c'est moins bruité

%2)