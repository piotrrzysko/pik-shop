angular
    .module('FileService', [])
    .factory('File', [FileService]);

function FileService() {

    return {
        convertFilesToBlob: convertFilesToBlob
    };

    function convertFilesToBlob(files) {
        var filesAsBlob = [];
        (files || []).forEach(function (file) {
            var fileAsDataURL = file.dataUrl;
            if (fileAsDataURL) {
                var fileAsBlob = dataURLtoBlob(fileAsDataURL);
                var f = new File([fileAsBlob], file.fileName);
                filesAsBlob.push(f);
            }
        });
        return filesAsBlob;
    }

    function dataURLtoBlob(dataurl) {
        var arr = dataurl.split(',');
        var mime = arr[0].match(/:(.*?);/)[1];
        var bstr = atob(arr[1]);
        var n = bstr.length;
        var u8arr = new Uint8Array(n);

        while (n--) {
            u8arr[n] = bstr.charCodeAt(n);
        }
        return new Blob([u8arr], {type: mime});
    }
}
